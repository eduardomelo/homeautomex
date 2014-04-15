using AutoMapper;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper.Mappers
{
    public class DispositivoAutoMapper : Profile
    {
        protected override void Configure()
        {
            Mapper.CreateMap<DispositivoModel, Dispositivo>();

            Mapper.CreateMap<Dispositivo, DispositivoModel>()
                .ForMember(e => e.ChaveAmbiente, opt => opt.MapFrom(e => e.Ambiente.Chave))
                .ForMember(e => e.ChavePorta, opt => opt.MapFrom(e => e.Porta.Chave));
            base.Configure();
        }
    }
}