using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class ModuloMap : EntityTypeConfiguration<Modulo>
    {
        public ModuloMap()
        {

            ToTable("MODULO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_MODULO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.NumeroIP).HasColumnName("NM_IP").IsRequired();
            Property(e => e.NumeroPorta).HasColumnName("NM_PORTA");
            Property(e => e.Nome).HasColumnName("DS_NOME");
            Property(e => e.NumeroMac).HasColumnName("NM_MAC");
            Property(e => e.Residencia.Chave).HasColumnName("CD_RESIDENCIA");

        
        }

    }
}
