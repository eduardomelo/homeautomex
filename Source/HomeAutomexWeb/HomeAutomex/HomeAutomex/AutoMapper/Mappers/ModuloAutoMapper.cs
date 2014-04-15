using AutoMapper;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper.Mappers
{
    public class ModuloAutoMapper : Profile
    {
        protected override void Configure()
        {
            Mapper.CreateMap<Modulo, ModuloModel>()
                .ForMember(e => e.ChaveResidencia, opt => opt.MapFrom(e => e.Residencia.Chave));

            Mapper.CreateMap<ModuloModel, Modulo>();

            base.Configure();
        }
    }
}